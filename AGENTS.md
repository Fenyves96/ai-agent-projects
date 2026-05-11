# AGENTS.md

## What this repo is

Three independent projects under a single root (`ai-agent-projects` placeholder README):

| Directory | Stack | Entrypoint | Tests |
|---|---|---|---|
| `budget-monitoring/` | Angular 21, SCSS, Bootstrap 5 | `src/main.ts` (standalone bootstrap) | Vitest (no tests written) |
| `contevent/contevent-backend/` | Spring Boot 3.2, Java 17, Maven | `ConteventBackendApplication.java` | JUnit 5 `@SpringBootTest` |
| `contevent/contevent-frontend/` | Angular 21, CSS | `src/main.ts` (standalone bootstrap) | Vitest (1 spec file) |

Each project has its own `package.json` / `pom.xml`. No root-level build tooling.

## Commands

### budget-monitoring
```bash
npm install          # install deps
ng serve             # dev server on :4200
ng build             # production build to dist/
ng test              # Vitest via Angular CLI
```

### contevent-backend
```bash
mvn spring-boot:run  # dev server on :8080
mvn test             # JUnit 5 tests
mvn clean install    # package
```

### contevent-frontend
```bash
npm install          # install deps
ng serve             # dev server on :4200 (note: default port may conflict with budget-monitoring)
ng build             # production build to dist/
ng test              # Vitest via Angular CLI
```

## Angular 21 quirks

- **Standalone API only** — no NgModules. Entry: `bootstrapApplication(App, appConfig)`.
- **Build system** — uses `@angular/build` (the new Vite-based builder, not `@angular-devkit/build-angular`).
- **Testing** — Vitest via `@angular/build:unit-test`, **not** Karma/Jasmine. Config in `tsconfig.spec.json` with `vitest/globals` types.
- **Prettier** — `printWidth: 100`, `singleQuote: true`, Angular HTML parser via `overrides`.
- **budget-monitoring** schematics have `skipTests: true` for all generators — `.spec` files are never auto-created and must be added manually.
- **contevent-frontend** uses plain `.css`; budget-monitoring uses `.scss`.

## Architecture notes

- **budget-monitoring** has 2 routes (`/` → Landing, `/add-spending` → AddSpending), uses Angular signals for in-memory state (`SpendingService`).
- **contevent-backend** is an empty Spring Boot app (no controllers yet), HTTP on port 8080.
- **contevent-frontend** has no routes, just a shell component.
- **Contevent purpose**: An application that helps people get to know each other based on common activities or events. For example, if someone has no company for playing darts, they can add an event in the app. People can apply for this event, and the user can accept or deny the application. Groups of users and companies can also create events. Users can find and apply for these events and form groups.
- No CI/CD, no GitHub Actions, no Docker config.
- contevent-backend has **no `.gitignore`** — `target/` and IDE files should be excluded.

## Workflow rules

- **Never commit changes automatically** — only commit when explicitly asked by the user, even in Build mode.
