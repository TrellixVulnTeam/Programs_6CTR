const express = require('express')
const router = express.Router();
var isLogged = require('../middleware/isLogged');
var user_controller = require('../controllers/usersController');

router.get('/logout', isLogged, user_controller.logout);

router.get('/register', isLogged, user_controller.register_get);
router.post('/register', user_controller.register_post);

router.get('/logins', isLogged, user_controller.login_get);
router.post('/login', user_controller.login);

router.get('/', isLogged, user_controller.index);

router.get('/usersList', user_controller.user_list);

router.get('/create', isLogged, user_controller.user_create_get);
router.post('/create', user_controller.user_create_post);

console.log("router user gotowy")

module.exports = router;

