export default class DownloadComponent extends HTMLElement {

    constructor() {
        super();
        this.message = document.querySelector("#message");
        this.innerHTML = `<button>${this.getAttribute('caption')}</button>`;
        this.button = this.querySelector('button');
        this.button.onclick = e => this.downloadRandomFile(e);
    }

    downloadRandomFile(e) {
        e.preventDefault();
        fetch('http://localhost:8080/resources/files')
            .then(response => {
                console.log(response.headers)
                const filename = response.headers.get('Content-Disposition').split('filename=')[1];
                response.blob().then(blob => {
                    let url = window.URL.createObjectURL(blob);
                    let a = document.createElement('a');
                    a.href = url;
                    a.download = filename;
                    a.click();
                });
            }).catch(error => {
                this.message.innerHTML = 'Something went wrong while downloading a random file :(';
                this.message.className = 'alert alert-danger';
            });
    }

}

customElements.define('download-component', DownloadComponent);