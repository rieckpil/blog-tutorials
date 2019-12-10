export class ApiClient {

  uploadFile(file: File) {
    let data = new FormData();
    data.append('file', file);

    return fetch('http://localhost:8080/resources/files', {
      method: 'POST',
      body: data
    });
  }

  downloadRandomFile() {
    return fetch('http://localhost:8080/resources/files');
  }
}
