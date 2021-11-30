from django.shortcuts import render, redirect
from django.contrib.auth import login, logout, authenticate
from django.urls import reverse #inaczej jak w starej wersji Django
from django.contrib import messages
from django.contrib.auth.forms import UserCreationForm
from salon.models import Samochod
from django.contrib.auth.decorators import login_required
from django.views.generic import ListView
from .models import Samochod
from .models import sell
from .models import wiadomosci
from .models import proba
from .forms import PostForm
from .forms import DataForm
from django.http import Http404, HttpResponse, HttpResponseRedirect
from django import forms





def borrow (request):
    jazda = proba.objects.all()
    return render(request, 'salon/borrow.html', {'jazda' : jazda})
def base(request):
    return render(request, 'salon/base.html')
    
def about(request):
    return render(request, 'salon/about.html')

def contact(request):
    samochody = Samochod.objects.all()
    # return render(request, 'salon/contact.html')
    if request.method=="POST":
        print("Sprawdzam czy to ma sens")
        
        form =  DataForm(request.POST)
        
        if form.is_valid():
            cd = form.cleaned_data
            #data = cd.get('data')
            data=request.POST.get('data')
            auto = request.POST.get('car')
            
            # print()
            username = request.POST.get('username')
            proba.objects.create(data=data, uczestnik=username, auto = auto)
            messages.success(request, "Data została dodana")
            
            return render(request, 'salon/contact.html', {'samochody' : samochody})
        messages.error(request, "Nie dodano terminu")
        return redirect(reverse('salon:contact'))
    else:
        form = DataForm()
    return render(request, 'salon/contact.html', {'form' : form, 'samochody':samochody})


def support(request):
    if request.method=="POST":
        form = PostForm(request.POST)

        if form.is_valid():
            cd = form.cleaned_data
            opis = cd.get('opis')
            username = request.POST.get('username')
            if not 0 < len(opis) <= 250:
                messages.error(request,"Wiadomość nie może być pusta, może mieć maks. 250 znaków!")
            else:
                wiadomosci.objects.create(wiadomosc=opis, nick=username)
                messages.success(request, "Wiadomość została wysłana")
                return redirect(reverse('salon:support'))
        messages.error(request, "Wiadomość nie została wysłana")
        return redirect(reverse('salon:support'))
    else:
        form = PostForm()
    return render(request, 'salon/support.html', {'form' : form})
    
def index(request):
    return render(request, 'salon/index.html')
# Create your views here.

def loguj(request):
    from django.contrib.auth.forms import AuthenticationForm
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)
       
        if form.is_valid():
            login(request, form.get_user())
            messages.success(request, "Witaj")
            return redirect(reverse('salon:index'))

    kontekst = {'form': AuthenticationForm()}
    return render(request, 'salon/loguj.html', kontekst)

def wyloguj(request):
    logout(request)
    messages.info(request, "Zostałeś wylogowany! ")
    return redirect(reverse('salon:index'))

def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            form.save()
            username = form.cleaned_data.get('username')
            raw_password = form.cleaned_data.get('password1')
            user = authenticate(username=username, password=raw_password)
            login(request, user)
            return redirect(reverse('salon:index'))
    else:
        form = UserCreationForm()
    return render(request, 'salon/signup.html', {'form':form})





def katalog(request):
    s=Samochod.objects.all()
    d=sell.objects.all()  
    if request.method=="POST":
        identyfikator = request.POST.get("id")
        b = Samochod.objects.get(id = identyfikator)
        uzytkownik = request.POST.get("uzytkownik")
        sell.objects.create(kupujacy = uzytkownik, marka = b.marka, model = b.model, Cena = b.cena)
        return render(request, 'salon/katalog.html', {'s': s, 'identyfikator' : identyfikator, 'b' : b} )
    return render(request, 'salon/katalog.html', {'s': s,})
    #s = Samochod.objects.all().order_by('cena')
    #output = ', '.join([q.opis for q in s])
    #d = Samochod.objects.get(cena=float(cena))
    #return render(request, 'salon/katalog.html', {'s': s})

def lista(request):
    spis = sell.objects.all().order_by('data')
    return render(request, 'salon/lista.html', {'spis' : spis}) 
    
def inbox(request):
    spis = wiadomosci.objects.all()
    return render(request, 'salon/inbox.html', {'spis' : spis})