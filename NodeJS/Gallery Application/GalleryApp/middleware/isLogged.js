const jwt = require('jsonwebtoken')


const isLogged = (req,res,next) =>
{
    
    try{
        const token = localStorage.getItem('token');

        const decode = jwt.verify(token, 'kodSzyfrujacy')

        req.user = decode
        console.log("reqUSER " + req.user._id)
        req.stan=true;
        next()
    }catch(err)
    {
        req.stan=false;
        next()
    }
}

module.exports = isLogged
