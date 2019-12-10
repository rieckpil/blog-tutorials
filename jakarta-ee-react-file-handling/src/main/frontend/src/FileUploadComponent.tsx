import React, {useState} from 'react';
import {Button, Form, Header} from "semantic-ui-react";

interface FileUploadComponentProps {
  uploadFile: (file: File) => void
}

const FileUploadComponent: React.FC<FileUploadComponentProps> = ({uploadFile}) => {

  const [fileContent, setFileContent] = useState<File | null>();

  return <React.Fragment>
    <Header as='h4'>Upload your file</Header>
    <Form onSubmit={event => fileContent && uploadFile(fileContent)}>
      <Form.Group widths='equal'>
        <Form.Field>
          <input placeholder='Select a file'
                 type='file'
                 onChange={event => event.target.files && setFileContent(event.target.files.item(0))}/>
        </Form.Field>
        <Button type='submit'>Upload</Button>
      </Form.Group>
    </Form>
  </React.Fragment>;
};

export default FileUploadComponent;
