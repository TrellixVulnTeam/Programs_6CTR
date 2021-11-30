const express = require('express')
const router = express.Router();

var tag_controller = require('../controllers/tagController');
router.get('/', tag_controller.index);

router.get('/tagList', tag_controller.tag_list);

router.get('/create', tag_controller.tag_create_get);

router.post('/create', tag_controller.tag_create_post);


console.log("router tag gotowy")

module.exports = router;