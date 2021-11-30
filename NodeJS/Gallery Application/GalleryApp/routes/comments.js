const express = require('express')
const router = express.Router();
var comment_controller = require('../controllers/commentController');
const authenticate = require('../middleware/authenticate');

router.post('/add/:id', authenticate, comment_controller.add);

router.get('/', comment_controller.index);

router.get('/commentList', comment_controller.comment_list);
router.get('/create', comment_controller.comment_create_get);
router.post('/create', comment_controller.comment_create_post);


console.log("router comment gotowy")

module.exports = router;