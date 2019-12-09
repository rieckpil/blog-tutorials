import React, {useState} from 'react';
import {Button, Container, Divider, Form, Header, Image} from "semantic-ui-react";
import {ApiClient} from "./ApiClient";

const App: React.FC = () => {
  const [fileContent, setFileContent] = useState<File | null>();

  return <Container text style={{marginTop: 10}}>
    <Image src='/jakartaEELogo.png' size='small' centered/>
    <Header as='h2' textAlign='center'>Jakarta EE & React File Handling</Header>
    <Header as='h4'>Upload your file</Header>
    <Form onSubmit={event => fileContent && new ApiClient().uploadFile(fileContent)}>
      <Form.Group widths='equal'>
        <Form.Field>
          <input placeholder='Select a file'
                 type='file'
                 onChange={event => event.target.files && setFileContent(event.target.files.item(0))}/>
        </Form.Field>
        <Button type='submit'>Upload</Button>
      </Form.Group>
    </Form>
    <Divider/>
    <Header as='h4'>Download a random file</Header>
    <Form>
      <Button type='submit' onClick={() => console.log(fileContent)}>Download</Button>
    </Form>
  </Container>;
};

export default App;
