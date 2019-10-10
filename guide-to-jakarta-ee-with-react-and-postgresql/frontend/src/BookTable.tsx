import React from "react";
import {Book} from "./App";

interface BookTableProps {
    books: Array<Book>
}

export const BookTable: React.FC<BookTableProps> = ({books}) => {
    return <div>
        {books.map(book => <div key={book.id}>
            <p>{book.id}</p>
            <p>{book.title}</p>
            <p>{book.genre}</p>
            <p>{book.excerpt}</p>
            <p>{book.isbn}</p>
            <p>{book.published}</p>
        </div>)}
    </div>;
};