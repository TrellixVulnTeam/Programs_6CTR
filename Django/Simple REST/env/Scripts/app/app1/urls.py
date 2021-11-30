from django . urls import path , include
from . import views
from rest_framework import routers
router = routers . DefaultRouter ()
router . register (' computers ' , views . ComputerViewSet )
urlpatterns = [
    path ('',include ( router . urls )),
    path ('api - auth / ', include ( 'rest_framework . urls ', namespace = ' rest_framework ' )),
    path ( ' computers / ' , views . computers , name = 'computers') ,
]