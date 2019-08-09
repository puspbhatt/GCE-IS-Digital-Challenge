import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { ShopListComponent } from './shop-list/shop-list.component';
import { ShopServiceService } from './service/shop-service.service';
import { ShopFormComponent } from './shop-form/shop-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ShopListComponent,
    ShopFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ShopServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
