export default class UploadComponent extends HTMLElement {

    constructor() {
        super();
        this.message = document.querySelector("#message");
        this.innerHTML = `<form>
            <input required type="file"></input>
            <button type="submit">${this.getAttribute('caption')}</button>
        </form>`;
        this.form = this.querySelector('form');
        this.form.onsubmit = e => this.uploadFile(e);
        this.file = this.querySelector('input');
    }

    uploadFile(e) {
        e.preventDefault();
        const formData = new FormData();
        formData.append('file', this.file.files[0]);

        fetch('http://localhost:8080/resources/files', {
            method: 'POST',
            body: formData
        }).then(response => {
            this.message.innerHTML = 'File upload was succesfull!';
            this.message.className = 'alert alert-success';
            this.form.reset();
        }).catch(error => {
            this.message.innerHTML = 'Something went wrong while uploading a file :(';
            this.message.className = 'alert alert-danger';
        });
    }

}

customElements.define('upload-component', UploadComponent);