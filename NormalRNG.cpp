#include<iostream>
#include<set>
#include<map>
#include<vector>
#include<fstream>
#include<string>
#include<random>
#include<math.h>
using namespace std;

set <string> s;
set<pair <int,int> > v;

int n = 0;
vector<int> v1,v2;
int st = 0;
int nd = 200000;

string generateString(){
	int n = rand()%20+1;
	string ret="";
	for(int i=0;i<n;i++){
		int c = rand()%26+'a';
		ret.append(" ");
		ret[ret.length()-1] = c;
	}
	while(s.count(ret)) ret+="a";
	return ret;
}
default_random_engine generator;
bool gen(double a,double b, pair<int, int > &p,normal_distribution<double> distribution){
    p.first = min(nd,max(st,(int)(distribution(generator)+a)));
    p.second = min(nd,max(st,(int)(distribution(generator)+b)));
    if(v.count(make_pair(p.first,p.second))){
        return false;
    }
    return true;
}
int main(){
    freopen("08normaltest.in","w",stdout); // change file name as you like
	ofstream fout("08normalrigh");


    double sz = 200000; // Number of generated points
	nd = sz;
    normal_distribution<double> distribution1(sz/2,sz/4);
	normal_distribution<double> distribution2(0.0,sz/8);
	normal_distribution<double> distribution3(0.0,sz/16);
    normal_distribution<double> citycountdist((int) pow(sz+.0,.3333),20);
	int l1 = (int) citycountdist(generator);
	for(int i=0;i<l1;i++){
        int a = min(nd,max(st,(int)distribution1(generator)));
        int b = min(nd,max(st,(int)distribution1(generator)));
		if(v.count(make_pair(a,b))){
            continue;
		}
		v1.push_back(a);
		v2.push_back(b);
        string s = generateString();
		v.insert(make_pair(a,b));
        cout<<a<<" "<<b<<" ";
        cout<<s<<endl;
        fout<<s<<endl;
        n++;
        int l2 = (int) citycountdist(generator);
        //cout<<l2<<endl;
        for(int j=0;j<l2;j++){
            pair<int,int> res;
            if(!gen((double)a,(double)b,res,distribution2)){
                continue;
            }
            v1.push_back(res.first);
            v2.push_back(res.second);
            s = generateString();
            v.insert(make_pair(res.first,res.second));
            cout<<res.first<<" "<<res.second<<" ";
            cout<<s<<endl;
            fout<<s<<endl;
            n++;
            int l3 = (int)citycountdist(generator);
            for(int k=0;k<l3;k++){
                if(!gen((double)a,(double)b, res,distribution3)){
                    continue;
                }
                v1.push_back(res.first);
                v2.push_back(res.second);
                s = generateString();
                v.insert(make_pair(res.first,res.second));
                cout<<res.first<<" "<<res.second<<" ";
                cout<<s<<endl;
                fout<<s<<endl;
                n++;
            }
        }

	}
	cout<<"-1 -1"<<endl;
	for(int i=0;i<n;i++){
        cout<<v1[i]<<" "<<v2[i]<<endl;
	}
	for(int i=0;i<n;i++){

		int a = rand()%nd+st;
		int b = rand()%nd+st;
		if(v.count(make_pair(a,b))){
            continue;
		}
		cout<<a<<" "<<b<<endl;
		fout<<"Not Found"<<endl;
	}
	cout<<"-1 -1"<<endl;
    return 0;
}


