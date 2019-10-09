import React from 'react';
import logo from './logo.svg';
import './App.css';
import {Button} from 'semantic-ui-react'

const App: React.FC = () => {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo"/>
                <p>
                    <code>Hello World!</code>
                </p>
                <Button>Click me for more information!</Button>
            </header>
        </div>
    );
}

export default App;
