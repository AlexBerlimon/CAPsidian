import generators

links_spec = {
    'ID' : generators.UUID(),
    'sourceNote': generators.Title(100),
    'targetNote': generators.TargetNote(100),
    'linkType' : generators.LinkType(),
}

with open('../db/data/db.capsidianLinks.csv', 'w') as links_csv:
    links_csv.write(generators.generate_csv(links_spec))