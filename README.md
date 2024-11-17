# Library Inventory & Platform

## Technology Stack
### Backend
- Gradle, Spring, Spring Security, JWT, Liquibase, Hibernate, Postgresql
### Frontend
- NextJS, Tailwind, Shadcn/ui

## Features
- Users can sign up and sign in
- Stateless user authentication with JWTs
- Keep inventory of different books
- Users can reserve books to borrow at the library
- Users can borrow books at the library
- Librarians can view stats of active or late borrows
- Books can be tagged

## Screenshots

![stats](screenshots\stats.png)
![inventory](screenshots\inventory.png)
![reservations](screenshots\reservations.png)

## Setup Backend
```md
1. Install the dependencies with Gradle
2. Run `docker compose up -d`
3. Configure application.properties 
4. Run the spring boot app
```

## Setup Frontend
```md
1. Install the dependencies with npm
2. Set BACKEND in `.env` pointing to the spring API, e.g. http://localhost:8090
3. Run `npm run dev`
```