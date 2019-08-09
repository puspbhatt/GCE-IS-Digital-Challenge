import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShopListComponent } from './shop-list/shop-list.component';
import { ShopFormComponent } from './shop-form/shop-form.component';

const routes: Routes = [
    { path: 'shops', component: ShopListComponent },
    { path: 'shops/:name', component: ShopListComponent },
    { path: 'addShop', component: ShopFormComponent }
];

@NgModule( {
    imports: [RouterModule.forRoot( routes , {
        onSameUrlNavigation: 'reload'
    })],
    exports: [RouterModule]
} )
export class AppRoutingModule { }