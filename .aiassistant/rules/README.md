# AI Assistant Rules (Pinned)

- Obey `rules/RULES.md` sections 4/5 plus Document Modularity Policy and Forward-Only Change Policy.
- Use host artifacts outside `rules/`; treat `rules/` as read-only submodule reference.
- Stack: Java 25 LTS (Maven), JWebMP Core/Client/Angular/TypeScript, Angular 20, GuicedEE Client, CRTP fluent API (no Lombok @Builder), Log4j2 logging, JSpecify nullness.
- Stage gates: Docs-first (Stages 1–3) with blanket approval noted; still record gates. Close loops across PACT → RULES → GUIDES → IMPLEMENTATION → diagrams.
- Glossary: topic-first precedence per `GLOSSARY.md`; link to topic glossaries before defining new terms.
- Diagrams: keep Mermaid sources under `docs/architecture/`; cite if generated via MCP (Mermaid HTTP endpoint).
