const express = require('express')
const router = express.Router();

var admin_controller = require('../controllers/adminController');
router.get('/', admin_controller.index);

router.get('/adminList', admin_controller.admin_list);

router.get('/create', admin_controller.admin_create_get);
router.post('/create', admin_controller.admin_create_post);

console.log("router admin gotowy")

module.exports = router;