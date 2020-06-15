package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  description 'Should return one book in a list'

  request {
    method GET()
    url '/books'
  }
  response {
    status OK()
    body '''\
            [
                {
                    "title": "Java 11",
                    "genre": "Technology",
                    "isbn": "42"
              }
            ]
        '''
    headers {
      contentType('application/json')
    }
  }
}
