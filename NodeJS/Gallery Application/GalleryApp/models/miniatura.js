const moongose = require('mongoose');

const MiniaturaSchema = new moongose.Schema({
    typ: String,
})

const Miniatura = moongose.model('Miniatura', MiniaturaSchema)

module.exports = Miniatura;