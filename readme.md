# Requirements

## Functional Requirements

- [x] Parse fixed-length files based on custom specification (MVP #1)
- [x] Store parsed records in a database (MVP #1)
- [x] Archive fixed-length files to block/object storage (MVP #1)
- [x] Store fixed-length file metadata in database - specifically the location where the archived file was stored (MVP #1)
- [x] View parsed records (3 views for MVP, see below) (MVP #2)
  - [x] Whenever I parse a file, I should immediately get the parsed data rendered on screen
    - [x] on the backend we get the fixed-length file, parse it according to a spec file, and return the JSON - representation of all records parsed form that file.
  - [ ] A view of all parsed records from the current user (even if we don't have real authentication)
    - [x] on the backend you get the request and query the database for all records associated with that user
    - [ ] then return that collection of records to the front end, where it is "unwrapped" and rendered on screen - (not just in the console)
  - [x] A view of all records that are of a specific type (for instance maybe I want to see all car records, - which are those parsed from the fixed-length files that a car spec file describes)
    - [x] on the backend you get the request and query the database for all records associated with specific spec - file.
    - [x] then return that collection of records to the front end, where it is "unwrapped" and rendered on screen - (not just in the console)
- [ ] Offer a web UI for users to access functionality (MVP #1-3)

## Stretch Goals

- [x] Authenticate users with registration and login
- [ ] Download copies of original fixed-length files from archive
- [ ] Download a JSON representation of all records from a fixed-length file
