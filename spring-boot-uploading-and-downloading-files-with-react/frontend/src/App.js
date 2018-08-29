import React, { Component } from 'react';
import reactLogo from './logo.svg';
import springBootLogo from './spring-boot-logo.png';
import './App.css';

class App extends Component {

  uploadImage = (e) => {
    e.preventDefault();
    console.log("uploading image");
  }

  downloadRandomImage = () => {
    console.log("downloading image");
    fetch('https://jsonplaceholder.typicode.com/todos/1')
      .then(response => response.json())
      .then(json => console.log(json));
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={reactLogo} className="App-logo" alt="reactLogo" />
          <img src={springBootLogo} className="App-logo" alt="springBootLogo" />
          <h1 className="App-title">Simple Uploading & Downloading of files with Spring Boot & React</h1>
        </header>
        <div className="App-intro">
          <h3>Upload a file</h3>
          <form>
            <input type="file"></input>
            <button type="submit" onClick={this.uploadImage}>Upload</button>
          </form>
        </div>
        <div className="App-intro">
          <h3>Download a random file</h3>
          <button onClick={this.downloadRandomImage}>Download</button>
        </div>
      </div>
    );
  }
}

export default App;
