using LinksService as service from '../services/links-service';

annotate service.Links with @restrict: [
    {
        grant: ['*'],
        to   : ['Administraor']
    },
    {
        grant: ['CREATE'],
        to   : ['Administrator']
    },
    {
        grant: [
            'READ',
            'UPDATE',
            'DELETE'
        ],
        to   : ['Administrator'],
    }
];
