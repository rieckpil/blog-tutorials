import React, {useEffect, useState} from 'react';
import './App.css';
import {BookTable} from "./BookTable";

export interface Book {
    id: number
    title: string
    author: string
    excerpt: string
    genre: string
    isbn: string
    published: string,
}

const App: React.FC = () => {

    const [data, setData] = useState<Array<Book> | Error>();
    useEffect(() => {
        fetch('http://localhost:9080/resources/books')
            .then(response => response.json() as Promise<Book[]>)
            .then(data => setData(data))
            .catch(error => setData(new Error(error.statusText)))
    }, []);

    if (!data) {
        return <div>Loading</div>
    } else if (data instanceof Error) {
        return <div>Error</div>
    } else {
        return (
            <BookTable books={data}></BookTable>
        );
    }
}

export default App;
