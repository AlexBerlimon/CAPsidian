using NotesService as service from '../services/notes-service';

annotate service.Notes with @restrict: [
    {
        grant: ['*'],
        to   : ['Administrators']
    },
    {
        grant: ['CREATE'],
        to   : ['User']
    },
    {
        grant: [
            'READ',
            'UPDATE',
            'DELETE', 
            'CREATE'
        ],
        to   : ['Administrators'],
    }
];
