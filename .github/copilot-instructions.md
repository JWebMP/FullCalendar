# Copilot Instructions – JWebMP FullCalendar

- Respect host `RULES.md` and submodule `rules/RULES.md` sections 4/5, Document Modularity, Forward-Only.
- Selected stacks: Java 25 LTS (Maven), JWebMP Core/Client/Angular/TypeScript, Angular 20, GuicedEE Client, CRTP fluent API (no builders), Log4j2 logging, JSpecify nullness.
- Docs-first workflow: complete PACT → RULES → GUIDES → IMPLEMENTATION before modifying code; blanket approval recorded but keep outputs stage-aligned.
- Do not write project docs inside `rules/`; link to modular topics there instead.
- Use topic-first glossary precedence (`GLOSSARY.md`); prefer architecture sources in `docs/architecture/`.
- Note MCP availability for diagrams (Mermaid HTTP endpoint in `.mcp.json`).
