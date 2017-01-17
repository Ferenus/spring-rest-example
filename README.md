A simple REST service which will return details of given Github repository. Details include:
- full name of repository
- description of repository
- git clone url
- number of stargazers
- date of creation formatted in requesters local date format

The API of the service look as follows:
GET /repositories/{owner}/{repositoryname}