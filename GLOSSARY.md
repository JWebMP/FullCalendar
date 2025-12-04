# Glossary (Topic-First)

## Precedence Policy
- Topic glossaries override host definitions within their scope.
- Host glossary acts as an index and adds only enforced prompt-language mappings or project clarifications.
- When in doubt, follow the linked topic file first, then return here for project-specific notes.

## Topic Glossaries
- Java: `rules/generative/language/java/GLOSSARY.md`
- Angular: `rules/generative/language/angular/GLOSSARY.md` (use with `angular-20.rules.md`)
- TypeScript: `rules/generative/language/typescript/GLOSSARY.md`
- JWebMP: `rules/generative/frontend/jwebmp/README.md` (index) and subtopics (Angular/TypeScript) for terminology
- GuicedEE Client: `rules/generative/backend/guicedee/client/GLOSSARY.md`
- Fluent API (CRTP): `rules/generative/backend/fluent-api/GLOSSARY.md`
- Logging: `rules/generative/backend/logging/README.md` (terminology within)
- JSpecify: `rules/generative/backend/jspecify/GLOSSARY.md`
- Testing: `rules/generative/platform/testing/README.md` plus `jacoco.rules.md`, `java-micro-harness.rules.md`, `browserstack.rules.md`
- CI/CD and secrets: `rules/generative/platform/ci-cd/README.md`, `rules/generative/platform/secrets-config/README.md`

## Project Notes
- Fluent API Strategy: CRTP is mandatory for this project; avoid any builder terminology unless explicitly allowed in future changes.
- Logging: Use Log4j2 naming for loggers and configuration examples; avoid alternate logger aliases.
- Nullness: Default to JSpecify annotations and guidance from the linked glossary.
- Angular Versioning: When referring to Angular concepts, assume Angular 20 semantics and APIs.
