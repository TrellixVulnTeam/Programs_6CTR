const express = require('express')
const router = express.Router();

var miniature_controller = require('../controllers/miniatureController');
router.get('/', miniature_controller.index);

router.get('/miniatureList', miniature_controller.miniature_list);

router.get('/create', miniature_controller.miniature_create_get);
router.post('/create', miniature_controller.miniature_create_post);

console.log("router miniature gotowy")

module.exports = router;