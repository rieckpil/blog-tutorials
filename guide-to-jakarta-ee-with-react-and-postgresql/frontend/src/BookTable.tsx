import React from "react";
import {Book} from "./App";
import {Table} from "semantic-ui-react";

interface BookTableProps {
    books: Array<Book>
}

export const BookTable: React.FC<BookTableProps> = ({books}) => {
    return (
        <Table celled>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>ID</Table.HeaderCell>
                    <Table.HeaderCell>Title</Table.HeaderCell>
                    <Table.HeaderCell>Genre</Table.HeaderCell>
                    <Table.HeaderCell>Excerpt</Table.HeaderCell>
                    <Table.HeaderCell>ISBN</Table.HeaderCell>
                    <Table.HeaderCell>Published</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {books.map(book =>
                    <Table.Row key={book.id}>
                        <Table.Cell>{book.id}</Table.Cell>
                        <Table.Cell>{book.title}</Table.Cell>
                        <Table.Cell>{book.genre}</Table.Cell>
                        <Table.Cell>{book.excerpt}</Table.Cell>
                        <Table.Cell>{book.isbn}</Table.Cell>
                        <Table.Cell>{book.published}</Table.Cell>
                    </Table.Row>
                )}
            </Table.Body>
        </Table>
    );
};