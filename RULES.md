# Project Rules – JWebMP FullCalendar

## Scope and Sources
- Host project rules; project docs stay outside `rules/` (submodule).
- Base policies: `rules/RULES.md` sections 4/5, Document Modularity Policy, and Forward-Only Change Policy.
- Stack selections (per prompt): Java 25 LTS (Maven), JWebMP Core/Angular/Client/TypeScript, Angular 20, GuicedEE Client, CRTP fluent API, Log4j2 logging, JSpecify defaults, TDD posture with docs-first flow.

## Topic Links (authoritative guidance)
- Language/Build: `rules/generative/language/java/java-25.rules.md`, `rules/generative/language/java/build-tooling.md`
- Angular 20: `rules/generative/language/angular/README.md`, `rules/generative/language/angular/angular-20.rules.md`
- TypeScript: `rules/generative/language/typescript/README.md`
- JWebMP: `rules/generative/frontend/jwebmp/README.md`, `rules/generative/frontend/jwebmp/angular/README.md`, `rules/generative/frontend/jwebmp/typescript/README.md`
- GuicedEE Client: `rules/generative/backend/guicedee/client/README.md`
- Fluent API: `rules/generative/backend/fluent-api/crtp.rules.md`
- Logging: `rules/generative/backend/logging/README.md`
- Nullness: `rules/generative/backend/jspecify/README.md`
- Testing: `rules/generative/platform/testing/README.md`, `rules/generative/platform/testing/jacoco.rules.md`, `rules/generative/platform/testing/java-micro-harness.rules.md`, `rules/generative/platform/testing/browserstack.rules.md`
- CI/CD & Secrets: `rules/generative/platform/ci-cd/README.md`, `rules/generative/platform/ci-cd/providers/github-actions.md`, `rules/generative/platform/secrets-config/env-variables.md`
- Architecture/TDD: `rules/generative/architecture/README.md`, `rules/generative/architecture/tdd/README.md`

## Behavioral and Technical Agreements
- Follow precise technical English; maintain traceability between PACT → RULES → GUIDES → IMPLEMENTATION.
- Use Markdown; avoid monolithic docs—prefer modular files linked from indexes.
- Forward-only edits: replace outdated anchors and update inbound links in the same change set.
- Respect JPMS exports and service loader bindings already present; keep GuicedEE scanning consistent.

## Project-Constrained Rules
- Fluent API: CRTP chaining only; avoid Lombok `@Builder`. Manual fluent setters return `(J) this` where needed.
- Logging: Default Log4j2 alignment; if Lombok is used elsewhere, prefer `@Log4j2` annotation.
- Angular: Use Angular 20 guidance (base + version override). Link to component/topic indexes instead of monolithic docs.
- Glossary precedence: Topic glossaries override root; see `GLOSSARY.md`.
- Diagrams: Maintain Mermaid sources under `docs/architecture/`; reference them in docs and guides.

## Traceability
- Architecture sources: `docs/architecture/README.md` (C4 + sequence + ERD).
- Prompt seed: `docs/PROMPT_REFERENCE.md`.
- PACT: `PACT.md`; GUIDES: `GUIDES.md`; Implementation plan: `IMPLEMENTATION.md`.
