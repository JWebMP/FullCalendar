# Prompt Reference

## Selected Stacks and Policies
- Language/Build: Java 25 LTS, Maven; JPMS module `com.jwebmp.plugins.fullcalendar`.
- Frameworks: JWebMP Core + Angular bridge, JWebMP Client, JWebMP TypeScript, JWebMP Angular, Angular 20.
- Backend/Runtime: GuicedEE Client (Vert.x underpins client bridge), Log4j2 logging policy.
- Structural: CRTP fluent API strategy (no Lombok @Builder), JSpecify for nullness defaults.
- Testing/Tooling: Jacoco, Java Micro Harness, BrowserStack (per prompt selections).
- Architecture: Specification-Driven Design, Documentation-as-Code, TDD posture.
- Change policy: Forward-only; Document Modularity enforced.

## Rules/Guides Anchors (Rules Repository paths)
- Language: `rules/generative/language/java/java-25.rules.md`, `rules/generative/language/java/build-tooling.md`
- Angular: `rules/generative/language/angular/README.md`, `rules/generative/language/angular/angular-20.rules.md`
- TypeScript: `rules/generative/language/typescript/README.md`
- JWebMP: `rules/generative/frontend/jwebmp/README.md`, `rules/generative/frontend/jwebmp/angular/README.md`, `rules/generative/frontend/jwebmp/typescript/README.md`
- GuicedEE Client: `rules/generative/backend/guicedee/client/README.md`
- Fluent API: `rules/generative/backend/fluent-api/crtp.rules.md`
- Logging: `rules/generative/backend/logging/README.md`
- JSpecify: `rules/generative/backend/jspecify/README.md`
- Testing: `rules/generative/platform/testing/README.md`, `rules/generative/platform/testing/jacoco.rules.md`, `rules/generative/platform/testing/java-micro-harness.rules.md`, `rules/generative/platform/testing/browserstack.rules.md`
- CI/CD + Secrets: `rules/generative/platform/ci-cd/README.md`, `rules/generative/platform/ci-cd/providers/github-actions.md`, `rules/generative/platform/secrets-config/env-variables.md`
- Architecture/TDD: `rules/generative/architecture/README.md`, `rules/generative/architecture/tdd/README.md`

## Diagram Links
- Architecture index: `docs/architecture/README.md`
- Context: `docs/architecture/c4-context.md`
- Container: `docs/architecture/c4-container.md`
- Component (FullCalendar): `docs/architecture/c4-component-fullcalendar.md`
- Sequence: `docs/architecture/sequence-render.md`, `docs/architecture/sequence-event.md`
- ERD: `docs/architecture/erd-events.md`

## Glossary Composition (topic-first)
- Primary glossaries: language/java, angular, typescript, fluent-api (CRTP), jspecify, jwebmp (core/angular/typescript), guicedee/client, logging, testing (jacoco/java micro harness/browserstack).
- Precedence: topic glossaries override host definitions for their scope; host `GLOSSARY.md` indexes them and captures enforced prompt-language mappings.

## Usage
- Load this reference at the start of any AI session for this repo.
- Keep host artifacts outside `rules/`; link to the submodule for authoritative guidance.
- Record MCP servers (Mermaid HTTP) in `.mcp.json` and cite when diagrams are generated via MCP.
