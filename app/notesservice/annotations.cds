using NotesService as service from '../../srv/notes-service';

annotate service.Notes with @fiori.draft.enabled;
annotate service.Notes with @odata.draft.enabled;

annotate service.Notes with @(
    Common.SemanticKey: [ID],
    UI                : {
        Identification     : [{Value: ID}],
        SelectionFields    : [
            title,
            content,
        ],
        LineItem           : [
            {Value: ID},
            {Value: title},
            {Value: content},
        ],
        HeaderInfo         : {
            TypeName      : '{i18n>noteTypeName}',
            TypeNamePlural: '{i18n>noteTypeNamePlural}',
            Title         : {Value: title},
            Description   : {Value: content}
        },
        Facets             : [
            {
                $Type : 'UI.ReferenceFacet',
                Label : '{i18n>noteGeneral}',
                Target: '@UI.FieldGroup#General'
            },
            {
                $Type : 'UI.ReferenceFacet',
                Label : '{i18n>noteDetails}',
                Target: '@UI.FieldGroup#Details'
            },
        ],
        FieldGroup #General: {Data: [
            {Value: title},
            {Value: content},
        ]},
        FieldGroup #Details: {Data: [
            {Value: createdAt},
            {Value: createdBy},
        ]}
    }
);

annotate NotesService.Notes with @title: '{i18n>noteTypeNamePlural}' {
    ID         @UI.Hidden;
    title      @title                  : '{i18n>noteTitle}';
    content    @title                  : '{i18n>noteContent}';
    modifiedAt @title                  : '{i18n>noteModifiedAt}';
    modifiedBy @title                  : '{i18n>noteModifiedBy}';
    createdAt  @title                  : '{i18n>noteCreatedAt}';
    createdBy  @title                  : '{i18n>noteCreatedBy}';

}

annotate NotesService.Notes : content with @UI.MultiLineText: true;
annotate NotesService.Notes with @UI.TextArrangement: #TextLast;
