### Java Web Servisleri

**Web Servisleri**, uygulamaları **dilden bağımsız** olarak, belirli protokoller kullanarak birbirleri ile haberleştirmede kullanılır. **Java Web Servisleri** iki standart altında incelenebilir. **JAX-WS(Web Services), JAX-RS(Rest Services)**. **JAX-WS, SOAP** protokolü için geliştirilmiş java standartıdır. **JAX-RS** ise **Rest** servisleri için standartları belirler.

### SOAP ve Rest arasındaki farklar

**SOAP(Simple Object Access Protocol)** bir protokoldür, kuralları çok fazladır ve kullanımı esnek değildir. Sadece **xml** verilerinin aktarılmasına olanak tanır. Yavaştır ve yüksek bant genişliği kullanır. Kendisine ait güvenlik mekanizması vardır.

**Rest(Representational State Transfer)** bir mimaridir. **HTML** protollerini kullanarak çalışır. Esnek bir kullanımı vardır. **Xml, Json, Text/Plain** vs. formatları destekler. Hızlıdır. Kendine ait güvenlik 
mekanizması yoktur.

### REST Mimarisinde Kaynaklar

**REST** mimarisinde her şey bir kaynaktır. Bu kaynaklar text dosyaları, html sayfaları, resimler videolar vs. her şey olabilir. Veri alışverişinde en çok kullanılan formatlar **XML, JSON**’dır. **REST** servisleri kaynakları sunarken; kaynaklara ulaşım biçimleri anlaşılabilir, sunulan kaynaklar tam olmalıdır.

### REST Mimarisinde Haberleşme

**REST** mimarisinde haberleşme protokolü olarak **HTTP** protokolünü kullanır. Kullanıcı **REST** servislerine **HTTP** isteği atar ve servisler **HTTP** cevabı ile cevap verirler.

Kaynaklara erişimde **URI** adresleri oluşturmak için kullanılan standartlar:

* Kaynakların kök adresleri çoğul isimlerden oluşmalıdır. 
* Uzun bir adres tanımlarken “ ” yerine “_” ya da “-” kullanmak daha uygundur.
* Adresleme tanımlamaları yapılırken küçük harf kullanmak daha uygundur.
* Tanımlanan bir adres daima kullanılabilir olmalıdır. Eğer adresin değişmesi gerekiyorsa 300 durum kodu ile yeni adrese yönlendirme yapılmalıdır.

### REST Mimarisinde Adresleme

Her kaynak **URI** adı verilen bir şekilde adreslenerek, kullanıcıların bu adres üzerinden kaynaklara ulaşılması sağlanır.

### REST Servislerinde Bulunması Gereken Özellikler

* Bir rest servisi **stateless** olmalıdır.

Server tarafında clienta ait bilgi saklanmamalıdır. 1 milyon kişinin istek attığı bir API düşünürsek 
her birinin oturum bilgisinin tutulması çok büyük maliyettir. Bunun yerine client her istek attıgında
gerekli bilgileri servera iletmelidir. Bunun da dezavantajları vardır. İnternet trafiğini arttırır ve
validasyon gerektiren yerlerde extra işlem yükü gerektirir. Oturum yönetimleri için HTTP BASIC ve Server Singed Token Authentication yöntemleri kullanılabilir. Spring Security stateless olarak ayarlanabiliyor.

* Caching ayaları yapılmalıdır.

Client tarafında serverdan gelen bilgiler cachelenebilir. Server gönderdiği verilerin cacheable olup olmadığını belirtmelidir. Bu performansı arttırır.

### JAX-RS

Java' da **Rest** servisleri için tanımlanmış standartları oluşturur. Bu standartları kullanarak ürünlerini yayınlayanlar vardır. Bu ürünler **Jersey, Resteasy, Apache CXF** vs. dir.

Kullanılan notasyonlar:

* **Path**: Resource sınıfı yada metoda adres tanımlamak için kullanılır.
* **GET**: Http GET metodu için kullanılır.
* **PUT**: Http PUT metodu için kullanılır.
* **POST**: Http POST metodu için kullanılır.
* **DELETE**: Http DELETE metodu için kullanılır.
* **Head**: Http HEAD metodu için kullanılır.
* **Produces**: Sınıf ya da metodun üreteceği kaynağın gönderilme formatını belirler(JSON, Xml vs)
* **Consumes**: Sınıf ya da metodun kabul edeceği verinin formatını belirler.
* **PathParam**: URL den alınacak parametre için.
* **QueryParam**: URL alınacak query parametreleri için.
* **HeaderParam**: HTTP Headerdan alınacak parametreler için.
* **CookieParam**: Cookieden parametre okumak için.
* **FormParam**: Formdaki alanları okumak için

### Idempotent Method

**HTTP** metotları arasından **idempotent** olanları vardır bunlar **delete** ve **put** metotlarıdır.
ÖRN **delete/12** delete isteği için ilk sefer hariç diğer isteklerde hep aynı sonuç dönecektir.
aynı şekilde **update/12** isteği içinde ilk sefer hariç diğer isteklerde hep aynı sonuç döner.
bu durum **idempotent** olarak adlandırılır.

Mesela **delete/last** gibi bir istek ile her zaman sondaki kaydı silebiliriz fakat idempotent özelliği bozduğundan dolayı kullanılmaz. Delete fonksiyonun doğru kullanımı delete/12 şeklindedir.

#### Post ve Put metodunun farkı nedir?

**Post** ve **Put** metodunun temelde bir farkı yoktur. İkisi de güncelleme ve ekleme işlemleri için kullanılabilir. Rest de metotların kullanımı ile ilgili katı bir standart yoktur. 

Aralarında ki fark **Put** metodunun **idempotent** olmasıdır. Yani serverda aynı istek için aynı cevap dönmelidir. Bunu kullanmak iyi bir yöntemdir. Yeni bir kaynak eklemek için **PUT** kullanılabilir fakat bu sefer idempotent özelliği bozulur. Bu yüzden yeni kaynak eklemek için post güncellemek için put kullanmak iyi bir yöntemdir.

### HTTP Durum Kodları

* 1xx: Bilgi amaçlı kullanılan kodlardır.
* 2xx: Başarılı durum kodudur.
* 3xx: Yönlendirme durum kodudur.
* 4xx: Client Hataları için durum kodudur.
* 5xx: Server Hataları için durum kodudur.

[Tüm durum kodları için](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)
