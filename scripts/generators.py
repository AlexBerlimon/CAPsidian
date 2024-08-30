import uuid
import random

class FieldValue:
    def generate(self) -> str:
        raise NotImplementedError()

class UUID(FieldValue):
    def generate(self) -> str:
        return str(uuid.uuid4())

class ChoicedString(FieldValue):
    def __init__(self, max_length: int, variants: list):
        self.variants = variants
        self.max_length = max_length

    def generate(self) -> str:
        output = ''
        while len(output) < self.max_length:
            random_word = random.choice(self.variants)
            if len(output) + len(random_word) > self.max_length:
                break
            output += random_word
        return output

class Title(ChoicedString):
    def __init__(self, max_length: 25):
        title_words = [
            'Title', 
            'First',
            'Second',
            'Third',
            'Fourth',
            'Fifth',
            'Sixth',
        ]
        super().__init__(max_length, title_words)

class Content(ChoicedString):
    def __init__(self, max_length: int):
        content_words = [
            'sample',
            'history',
            'about',
            'author',
            'and',
            'devops',
            'and his dog',
        ]
        super().__init__(max_length, content_words)


class TargetNote(ChoicedString):
    def __init__(self, max_length: 25):
        target_words = [
            'Reference',
            'Mention',
            'Related',
            'Supplement',
            'Annotation',
        ]
        super().__init__(max_length, target_words)

class LinkType(ChoicedString):
    def __init__(self):
        link_types = ['None', 'Internal', 'External', 'File', 'Media', 'Task', 'Custom']
        super().__init__(max_length=15, variants=link_types)

def generate_line(specification, sep=';', end='\n') -> str:
    return sep.join([gen.generate() for gen in specification.values()]) + end

def generate_header(specification, sep=';', end='\n') -> str:
    return sep.join(specification.keys()) + end

def generate_csv(specification, lines=100, sep=';', end='\n') -> str:
    csv = generate_header(specification, sep=sep, end=end)
    for _ in range(lines):
        csv += generate_line(specification, sep=sep, end=end)
    return csv