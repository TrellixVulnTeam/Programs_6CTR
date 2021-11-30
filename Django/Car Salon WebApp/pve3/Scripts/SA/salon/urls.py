from django.conf.urls import url
from salon import views
from django.urls import path
from django.contrib.auth.forms import UserCreationForm
from django.views.generic.edit import CreateView



app_name = 'salon' # przestrzeń nazw aplikacji
urlpatterns = [
    path('', views.index, name='index'),
    path('loguj/', views.loguj, name='loguj'),
    path('wyloguj/', views.wyloguj, name='wyloguj'),
    path('signup/', views.signup, name='signup'),
    path('base/', views.base, name='base'),
    path('contact/', views.contact, name='contact'),
    path('support/', views.support, name='support'),
    path('katalog/', views.katalog, name='katalog'),
    path('lista/', views.lista, name='lista'),
    path('inbox/', views.inbox, name='inbox'),
    path('about/', views.about, name='about'),
    path('borrow/', views.borrow, name='borrow'),
  
   
]