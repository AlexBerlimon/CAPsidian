using LinksService as service from '../../srv/links-service';

annotate service.Links with @fiori.draft.enabled;
annotate service.Links with @odata.draft.enabled;

annotate service.Links with @(
    Common.SemanticKey: [ID],

    UI : {
        Identification  : [{Value: ID}],
        SelectionFields  : [
            type,
            targetNote_ID,
            sourceNote_ID
        ],
        LineItem  : [
            {Value: ID},
            {Value: targetNote.ID},
            {Value: sourceNote.ID},
            {Value: type}
        ],
        HeaderInfo  : {
            $Type : 'UI.HeaderInfoType',
            TypeName : '{i18n>linkGeneral}',
            TypeNamePlural : '{i18n>linkGeneralPlural}',
        },
    }
    );

annotate service.Links with {
    sourceNote @Common.ValueList : {
        $Type : 'Common.ValueListType',
        CollectionPath : 'Notes',
        Parameters : [
            {
                $Type : 'Common.ValueListParameterInOut',
                LocalDataProperty : sourceNote_ID,
                ValueListProperty : 'ID',
            },
            {
                $Type : 'Common.ValueListParameterDisplayOnly',
                ValueListProperty : 'title',
            },
            {
                $Type : 'Common.ValueListParameterDisplayOnly',
                ValueListProperty : 'content',
            },
        ],
    }
};

annotate service.Links with {
    targetNote @Common.ValueList : {
        $Type : 'Common.ValueListType',
        CollectionPath : 'Notes',
        Parameters : [
            {
                $Type : 'Common.ValueListParameterInOut',
                LocalDataProperty : targetNote_ID,
                ValueListProperty : 'ID',
            },
            {
                $Type : 'Common.ValueListParameterDisplayOnly',
                ValueListProperty : 'title',
            },
            {
                $Type : 'Common.ValueListParameterDisplayOnly',
                ValueListProperty : 'content',
            },
        ],
    }
};

