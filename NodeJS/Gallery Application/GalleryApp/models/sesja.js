const mongoose = require('mongoose')

const SesjaSchema = new mongoose.Schema({


    id: {
        type: String,
        unique: true,
    },
    token: {
        type: String,
    },
    waznosc: {
        
        type: Date,
    },




})

const Sesja = mongoose.model('Sesja', SesjaSchema)

module.exports = Sesja;