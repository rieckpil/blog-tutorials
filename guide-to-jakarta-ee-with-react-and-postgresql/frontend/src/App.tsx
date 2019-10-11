import React, {useEffect, useState} from 'react';
import './App.css';
import {BookTable} from "./BookTable";
import {Container, Dimmer, Header, Loader, Message, Image} from "semantic-ui-react";

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

    let content;

    if (!data) {
        content = (
            <Dimmer active inverted>
                <Loader>Loading</Loader>
                <Image src='/short-paragraph.png'/>
            </Dimmer>
        );
    } else if (data instanceof Error) {
        content = <Message negative>An error occurred while fetching the data</Message>;
    } else {
        content = <BookTable books={data}></BookTable>;
    }

    return (
        <Container>
            <Header as='h2'>Available Books</Header>
            {content}
        </Container>
    );
}

export default App;
