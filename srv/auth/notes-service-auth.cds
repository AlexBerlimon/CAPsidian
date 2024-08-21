using NotesService as service from '../services/notes-service';

annotate service.Notes with @restrict: [
    {
        grant: ['*'],
        to   : ['Administraor']
    },
    {
        grant: ['CREATE'],
        to   : ['User']
    },
    {
        grant: [
            'READ',
            'UPDATE',
            'DELETE'
        ],
        to   : ['User'],
        where: 'createdBy = $user'
    }
];
