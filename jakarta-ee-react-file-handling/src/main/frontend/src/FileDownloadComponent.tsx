import React from 'react';
import {Button, Form, Header} from "semantic-ui-react";

interface FileDownloadComponentProps {
  downloadFile: () => void
}

const FileDownloadComponent: React.FC<FileDownloadComponentProps> = ({downloadFile}) => {
  return <React.Fragment>
    <Header as='h4'>Download a random file</Header>
    <Form>
      <Button type='submit' onClick={downloadFile}>Download</Button>
    </Form>
  </React.Fragment>;
};

export default FileDownloadComponent;
