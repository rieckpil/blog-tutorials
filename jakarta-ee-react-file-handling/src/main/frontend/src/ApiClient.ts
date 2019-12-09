export class ApiClient {

  uploadFile(file: File) {
    let data = new FormData();
    data.append('file', file);
    data.append('name', file.name);

    return fetch('http://localhost:9080/resources/files', {
      method: 'POST',
      body: data
    });
  }

  downloadRandomFile() {
    return fetch('http://localhost:9080/resources/files');
  }
}
