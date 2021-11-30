const mongoose = require('mongoose')

const KomentarzSchema = new mongoose.Schema({

    user: {
        type: String,

    },

    tresc: {
        type: String,

    },
    id_zdjecia: {
        type: String,
    }


})

const Komentarz = mongoose.model('Komentarz', KomentarzSchema);

module.exports = Komentarz