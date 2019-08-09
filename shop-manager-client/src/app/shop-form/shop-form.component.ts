import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ShopServiceService } from '../service/shop-service.service';
import { Shop } from '../model/shop';

@Component( {
    selector: 'app-shop-form',
    templateUrl: './shop-form.component.html',
    styleUrls: ['./shop-form.component.css']
} )
export class ShopFormComponent {

    shop: Shop;

    constructor( private route: ActivatedRoute, private router: Router, private shopService: ShopServiceService ) {
        this.shop = new Shop();
    }

    onSubmit() {
        this.shopService.save( this.shop ).subscribe( result => this.gotoShopList() );
    }

    gotoShopList() {
        this.router.navigate( ['/shops'] );
    }

}
