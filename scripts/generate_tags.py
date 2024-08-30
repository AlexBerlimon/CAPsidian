import generators

tags_spec = {
    'ID' : generators.UUID(),
    'title': generators.Title(100),
}

with open('../db/data/db.capsidianTags.csv', 'w') as tags_csv:
    tags_csv.write(generators.generate_csv(tags_spec))