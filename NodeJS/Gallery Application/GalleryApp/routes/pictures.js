const express = require('express')
const router = express.Router();

//odniesienie do middleware
var authenticate = require('../middleware/authenticate');
var isLogged = require('../middleware/isLogged');

//aby skorzystać z autentyfikacji w danej podstronie wystarczy dodać

//musimy dodać kontroler aby był widoczny dla naszych ścieżek 
var picture_controller = require('../controllers/pictureController');

//w tym miejscu zamiast definiować operacje, ustalamy jedynie ścieżki
//przypisujemy potrzebne elementy do budowy CRUD

router.get('/', isLogged, picture_controller.index);
// dostęp do funkcji pod adresem localhost:8000/pictures

router.get('/picturesList', authenticate, picture_controller.picture_list);
//Dostęp do funkcji pod adresem localhost:8000/pictures/picturesList

router.post('/create', authenticate, picture_controller.picture_create_post);
// Dostęp do funkcji pod adresem http://localhost:8000/pictures/create
router.get('/create', isLogged, authenticate, picture_controller.picture_create_get);
// Dostęp do funkcji pod adresem http://localhost:8000/pictures/create

router.get('/:id', isLogged, picture_controller.picture_info);
//Dostęp do funkcji pod adresem http://localhost:8000/picture/id


 router.post('/like', authenticate, picture_controller.like);

router.get('/update/:id', isLogged, authenticate, picture_controller.picture_update_get);
// Dostęp do funkcji pod adresem http://localhost:8000/pictures/update:id
router.post('/update/:id', picture_controller.picture_update_post);
//Dostęp do funkcji pod adresem http://localhost:8000/pictures/update:id

router.post('/delete', authenticate, picture_controller.picture_delete_post);

router.post('/upload', authenticate, picture_controller.picture_upload_post);

console.log("router picture gotowy")




module.exports = router;



