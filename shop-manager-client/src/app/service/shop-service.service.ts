import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Shop } from '../model/shop';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ShopServiceService {

    private shopsUrl: string;

    constructor( private http: HttpClient ) {
        this.shopsUrl = 'http://localhost:8080/shops';
    }

    public findAll(): Observable<Shop[]> {
        return this.http.get<Shop[]>( this.shopsUrl )
    }

    public search( key: string, value: string ): Observable<Shop[]> {
        const params = new HttpParams()
            .set( 'value', value );
        return this.http.get<Shop[]>( this.shopsUrl + '/' + key, { params } );
    }

    public save( shop: Shop ) {
        return this.http.post<Shop>( this.shopsUrl, shop );
    }
}
