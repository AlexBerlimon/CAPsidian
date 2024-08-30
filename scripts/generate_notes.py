import generators

notes_spec = {
    'ID' : generators.UUID(),
    'title': generators.Title(5),
    'content': generators.Content(5),
}
print(generators.generate_csv(notes_spec))

with open('../db/data/db.capsidianNotes.csv', 'w') as notes_csv:
    print(generators.generate_csv(notes_spec), file=notes_csv)
    