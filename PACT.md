---
version: 2.0
title: Human–AI Collaboration Pact
project: JWebMP / FullCalendar
date: 2025-12-03
authors: [Maintainers, Contributors, AI Assistants]
---

# Collaboration Pact

## Purpose
- Align humans and AI on forward-only, documentation-first delivery for the JWebMP FullCalendar component (Java 25 LTS, Maven).
- Close loops across `PACT` → `RULES` → `GUIDES` → `IMPLEMENTATION`, with diagrams captured as docs-as-code.
- Treat the Rules Repository (`rules/` submodule) as the authoritative source; host-specific artifacts stay outside `rules/`.

## Principles
- Continuity: Context lives in this repo; diagrams and docs remain the single source of truth.
- Transparency: Declare constraints (stage gates, stack selections, CRTP fluent API, Log4j2 logging, JSpecify defaults).
- Respect: Use precise technical English; attribute shared authorship; prefer small, reviewable increments.
- Closure: Every change links backward (rules/diagrams) and forward (guides/implementation); no TODO placeholders.

## Working Mode
- Stage approvals: Blanket approval per prompt inputs; gates are recorded but auto-approved.
- Documentation-first: Stages 1–3 produce docs/diagrams only; Stage 4 may add scaffolding/configs.
- Forward-only: Replace outdated docs rather than retaining legacy anchors; update inbound links in the same change set.
- MCP: Mermaid MCP server registered via `.mcp.json` for diagram support; cite when used.

## Traceability Map
- Rules anchor: `RULES.md` (host) → `rules/RULES.md` sections 4/5 + Document Modularity + Forward-Only.
- Guides anchor: `GUIDES.md` (host) → selected modular topics (Java 25, CRTP fluent API, GuicedEE Client, JWebMP Angular/TypeScript, Angular 20, Logging, JSpecify, testing).
- Implementation anchor: `IMPLEMENTATION.md` (host) → code modules, service registrations, build/CI/env.
- Diagrams anchor: `docs/architecture/README.md` with C4/sequence/ERD sources; linked from all artifacts.
- Glossary anchor: `GLOSSARY.md` topic-first; precedence favors topic glossaries over root entries.

## Behaviors (excerpt from Rules sections 4 & 5)
- Communicate clearly; keep tone factual and collaborative.
- Maintain naming/module consistency (JPMS exports, GuicedEE services).
- Use Markdown, link sources, and state limitations.

## Logging & Observability
- Default logging guidance: Log4j2; align examples/configuration accordingly (no alternate logging annotations).
- Testing posture: Jacoco coverage, Java Micro Harness, BrowserStack (per selections) referenced in guides.

## Outcomes for This Run
- Adopt Rules Repository submodule (already present) and surface links in README.
- Produce Stage 1–3 docs (PACT, RULES, GUIDES, GLOSSARY, IMPLEMENTATION, PROMPT_REFERENCE, architecture diagrams, migration plan); Stage 4 adds CI/env/AI workspace wiring and link updates.
- Keep project-specific docs outside `rules/`; ensure glossary precedence and CRTP fluent API enforcement are explicit.
