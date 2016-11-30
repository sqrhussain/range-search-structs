#include<fstream>
#include<iostream>
#include<set>
#include<map>
#include<vector>
#include<string>
using namespace std;
set <string> s;
set<pair <int,int> > v;
map<pair<int,int> , string> m;
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

int main(){
    freopen("rangetest.in","w",stdout);
	ofstream fout("rangeright");
	vector<int> v1,v2;
	int lim = 20000; // Number of generated points
	int st = 10;
	int n = lim;
	for(int i=0;i<lim;i++){
		int a = rand()%lim+st;
		int b = rand()%lim+st;
		if(v.count(make_pair(a,b))){
            n--;
            continue;
		}
		v1.push_back(a);
		v2.push_back(b);
        string s = generateString();
		m[make_pair(a,b)]=s;
		v.insert(make_pair(a,b));
        cout<<a<<" "<<b<<" ";
        cout<<s<<endl;
		
	}
	cout<<"-1 -1"<<endl; // a line containing '-1 -1' separated insertions from queries
	for(int i=0;i<lim/100;i++){
		int a,b,c,d;
		a = rand()%lim+st;
		b = rand()%lim+st;
		c = rand()%lim+st;
		d = rand()%lim+st;
		int A,B,C,D;
		A = min(a,b);
		B = min(c,d);
		C = max(a,b);
		D = max(c,d);
		cout<<A<<" "<<B<<" "<<C<<" "<<D<<endl;
		int P=0;
		for(auto M:m){
			int x,y;
			x = M.first.first;
			y = M.first.second;
			if(x>=A && x<C && y>=B && y<D){
				P++;
			}
		}
		fout<<P<<endl;
	}
	cout<<"-1 -1 -1 -1"<<endl;
    return 0;
}

