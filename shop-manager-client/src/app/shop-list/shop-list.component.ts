import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Shop } from '../model/shop';
import { ShopServiceService } from '../service/shop-service.service';
@Component( {
    selector: 'app-shop-list',
    templateUrl: './shop-list.component.html',
    styleUrls: ['./shop-list.component.css']
} )
export class ShopListComponent implements OnInit {

    shops: Shop[];
    @ViewChild( 'searchBy' ) searchBy: ElementRef;
    @ViewChild( 'searchKey' ) searchKey: ElementRef;
    constructor( private shopService: ShopServiceService, private router: Router ) {

    }

    ngOnInit() {
        this.shopService.findAll().subscribe( data => {
            this.shops = data;
        } )
    }

    search() {
        let key = this.searchBy.nativeElement.value;
        let value = this.searchKey.nativeElement.value;
        this.shopService.search( key, value ).subscribe( data => {
            this.shops = data;
        } );
    }


}
