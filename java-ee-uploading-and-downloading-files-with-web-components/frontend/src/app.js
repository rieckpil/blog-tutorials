class IndexView {
    constructor() {
        this.uploadButton = document.querySelector("#upload");
        this.form = document.querySelector("#upload-form");
        this.form.onsubmit = e => this.uploadFile(e);
        this.message = document.querySelector("#message");
        this.file = document.querySelector("#file");
        this.downloadButton = document.querySelector("#download");
        this.downloadButton.onclick = e => this.downloadRandomFile(e);
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
        }).catch(error => {
            this.message.innerHTML = 'Something went wrong while uploading a file :(';
        });
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
            });
    }
}

new IndexView();