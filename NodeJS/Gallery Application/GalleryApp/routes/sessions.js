const express = require('express')
const router = express.Router();

var session_controller = require('../controllers/sessionController');
router.get('/', session_controller.index);

router.get('/sessionList', session_controller.session_list);

router.get('/create', session_controller.session_create_get);

router.post('/create', session_controller.session_create_post);

console.log("router session gotowy")

module.exports = router;