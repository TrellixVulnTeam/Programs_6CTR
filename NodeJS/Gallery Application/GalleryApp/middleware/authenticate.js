const jwt = require('jsonwebtoken')

const authenticate = (req,res,next) => {
    try{
        const token = localStorage.getItem('token');
        // const token = req.headers.authorization;
        console.log("To chyba jest token !!!!!!!! " + token)
        const decode = jwt.verify(token, 'kodSzyfrujacy')

        req.user = decode
        console.log("reqUSER " + req.user._id)
        next()
    }
    catch(err)
    {
        res.render('logins')
    }
}
module.exports = authenticate